require 'spec_helper'

RSpec.describe 'Parking Lot', type: :aruba do
  let(:command) { run "parking_lot" }
  before(:each) { command.write("create_parking_lot 3\n") }

  it "can create a parking lot" do
    stop_all_commands
    expect(command.output).to end_with("Created a parking lot with 3 slots\n")
  end

  it "can park a car" do
    command.write("park KA-01-HH-3141 Black\n")
    stop_all_commands
    expect(command.output).to end_with("Allocated slot number: 1\n")
  end
  
  it "can unpark a car" do
    command.write("park KA-01-HH-3141 Black\n")
    command.write("leave 1\n")
    stop_all_commands
    expect(command.output).to end_with("Slot number 1 is free\n")
  end
  
  it "can report status" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("status\n")
    stop_all_commands
    expect(command.output).to end_with(<<-EOTXT
Slot No.    Registration No    Colour
1           KA-01-HH-1234      White
2           KA-01-HH-3141      Black
3           KA-01-HH-9999      White
EOTXT
)
  end

  it "parking is full" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("park KA-01-HH-9997 Red\n")
    stop_all_commands
    expect(command.output).to end_with("Sorry, parking lot is full\n")
  end

  it "cannot unpark car from diff size index" do
    command.write("leave 5\n")
    stop_all_commands
    expect(command.output).to end_with("Not found\n")
  end

  it "registration numbers for cars with given colour" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("registration_numbers_for_cars_with_colour White\n")
    stop_all_commands
    expect(command.output).to end_with("KA-01-HH-1234, KA-01-HH-9999\n")
  end

  it "registration numbers for cars with given colour not found exception" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("registration_numbers_for_cars_with_colour Red\n")
    stop_all_commands
    expect(command.output).to end_with("Not found\n")
  end
  
  it "slot numbers for cars with given colour" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("slot_numbers_for_cars_with_colour White\n")
    stop_all_commands
    expect(command.output).to end_with("1, 3\n")
  end

  it "slot numbers for cars with given colour not found" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("slot_numbers_for_cars_with_colour Red\n")
    stop_all_commands
    expect(command.output).to end_with("Not found\n")
  end

  it "slot number for registration number" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("slot_number_for_registration_number KA-01-HH-3141\n")
    stop_all_commands
    expect(command.output).to end_with("2\n")
  end

  it "slot number for registration number not found" do
    command.write("park KA-01-HH-1234 White\n")
    command.write("park KA-01-HH-3141 Black\n")
    command.write("park KA-01-HH-9999 White\n")
    command.write("slot_number_for_registration_number KA-01-HH-3142\n")
    stop_all_commands
    expect(command.output).to end_with("Not found\n")
  end

end
